import {useEffect, useState} from 'react';
import './App.css';
import api from "./api.js";
import FeedBackModal from "./Components/FeedbackModal.jsx";
import WelcomeModal from "./Components/WelcomeModal.jsx";
import WordMapper from "./Components/WordMapper.jsx";
import PromptMapper from "./Components/PromptMapper.jsx";

function App() {
    const [guess, setGuess] = useState("");
    const [guesses, setGuesses] = useState([]);
    const [prompt, setPrompt] = useState(null);
    const [message, setMessage] = useState("");
    const [tries, setTries] = useState(0);
    const [shake, setShake] = useState(false);
    const [showModal, setShowModal] = useState(localStorage.getItem("showModal") === null);
    const [gameOver, setGameOver] = useState(false);
    const [gameWin, setGameWin] = useState(false);
    const [viewGameState, setViewGameState] = useState(false);


    useEffect(() => {
        api.get("/Blurtle/getScrambledWord")
            .then(response => setPrompt(response.data))
            .catch(error => console.error("Error fetching data:", error));
    }, []);

    const updateModal = () => {
        setShowModal(false);
        localStorage.setItem("showModal", "false");
    }

    const handleGameState = () => {
        setViewGameState(true);
    }

    const handleSubmit = async (e) => {

        e.preventDefault();

        if(tries >= 5){
            triggerShake();
            setMessage('Out of Attempts.  Try again tomorrow!')
            return;
        }
        setMessage("");
        if (guess.trim().length < 1) {
            triggerShake();
            setMessage("Please enter a guess.");
            return;
        }


        console.log("Sending guess: ", guess);

        try{
            const response = await api.post("/Blurtle/postGuessWord", { guessWord : guess });
            const data = response.data;
            if(!data.validWord){
                triggerShake();
                setMessage("Not in game dictionary.  Please try again");
                return;
            }
            //Count the amount of successful attempts taken (valid words)
            setTries(prev => prev + 1);

            console.log("Response: ", data);

            setGuesses(prev => [
                ...prev,
                {
                    word: data.guessWord.toUpperCase(),
                    exactMatch: data.exactMatch,
                    validGuess: data.validGuess,
                    validWord: data.validWord,
                    score: data.score
                }
            ]);

            if( data.exactMatch ){
                setGameWin(true);
                setGameOver(true);
            }
            else if( tries >= 4){
                setGameOver(true);
            }

            setGuess("");
        } catch (err){
            triggerShake();
            setMessage("Unable to contact server");
            console.error(err);
        }

    };

    const handleRescramble = async () => {
        try{
            const response = await api.get("/Blurtle/rescramble");
            setPrompt(response.data);
        }
        catch(error){
            console.error(error);
        }
    }



    const triggerShake = () => {
        setShake(false);

        requestAnimationFrame(() => {
           setShake(true);
        });
    };

  return (


      <div className="app-container">
          {showModal && <WelcomeModal updateModal={updateModal}/>}

          { (gameOver && !viewGameState) && <FeedBackModal feedback={gameWin ? "Great job!" : "Sorry! Out of guesses!"}
                                                           message="Check in tomorrow for a new challenge" onClick={handleGameState} guess={guesses.at(-1)}/> }


      <h1>Blurtle!</h1>
        <h4>Unscramble the letters to form a word.</h4>

          {prompt && <PromptMapper word={prompt.scrambledWord.toUpperCase()} guess={guess.toUpperCase()}></PromptMapper>}
        <button disabled={gameOver} className="scramble-button" onClick={handleRescramble}> Rescramble! </button>
        <form id="guessForm" onSubmit={handleSubmit} >
            <input
                disabled={gameOver}
                className={`guess-input ${shake ? "shake" : ""}`}
                onAnimationEnd={() => setShake(false)}
                type="text"
                value={guess}
                maxLength={15}
                onChange={(e) => setGuess(e.target.value)}
            />

          <br />
          <br />

          <button type="submit" disabled={gameOver}>Guess</button>
        </form>

          {message && (
              <div style={{ marginTop: "10px", fontSize: "18px" }}>
                  {message}
              </div>
          )}
          <p>{tries}/5 attempts used</p>
        <div style={{ marginTop: "30px" }}>
          {guesses.map((g, index) => (

              <div key={index} >
                <WordMapper guess={g} />
              </div>
          ))}
        </div>

      </div>
  );
}

export default App
