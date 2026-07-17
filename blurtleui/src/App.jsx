import {useEffect, useState} from 'react';
import reactLogo from './assets/react.svg';
import viteLogo from './assets/vite.svg';
import heroImg from './assets/hero.png';
import './App.css';
import api from "./api.js";

function App() {
    const [guess, setGuess] = useState("");
    const [guesses, setGuesses] = useState([]);
    const [prompt, setPrompt] = useState(null);
    const [message, setMessage] = useState("");
    const [tries, setTries] = useState(0);
    const [shake, setShake] = useState(false);
    const API_BASE = import.meta.env.VITE_API_BASE_URL;



    useEffect(() => {
        api.get("/Blurtle/getScrambledWord")
            .then(response => setPrompt(response.data))
            .catch(error => console.error("Error fetching data:", error));
    }, []);


    const handleSubmit = async (e) => {

        e.preventDefault();


        if(tries >= 5){
            triggerShake();
            setMessage('Out of Attempts.  Try again tomorrow!')
            // alert("Out of Attempts.  Try again tomorrow!");
            return;
        }
        setMessage("");
        if (guess.trim().length < 1) {
            triggerShake();
            setMessage("Please enter a guess.");
            // alert("Please enter a guess.");
            return;
        }


        console.log("Sending guess: ", guess);

        try{
            const res = await api.post("/Blurtle/postGuessWord", { guessWord : guess });
            const data = res.data;
            if(!data.validWord){
                triggerShake();
                setMessage("Not in game dictionary.  Please try again");
                return;
            }
            //Count the amount of successful attempts taken (valid words)
            setTries(prev => prev + 1);

            console.log("Response: ", res.data);

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
            setGuess("");
        } catch (err){
            triggerShake();
            setMessage("Unable to contact server");
            console.error(err);
        }

    };


    const triggerShake = () => {
        setShake(false);

        requestAnimationFrame(() => {
           setShake(true);
        });
    };

  return (


      <div className="app-container">
        <h1>Blurtle</h1>
        <h4>Unscramble the letters to form a word. <br/> Any word made from the available letters counts, but max points for using all of them!</h4>
        <div>
            <p className="rules">
                <span className="red-text"> Red </span>= Valid Word, but incorrect use of available letters<br/>
                <span className="blue-text"> Blue </span>= Valid Guess using only available letters, but not max points.<br/>
                <span className="green-text">Green </span>= Perfect Score!
            </p>
        </div>



          {prompt && <h4 style={{
              color: "#808080",
              fontWeight: "bold"
          }}>{prompt.scrambledWord}</h4>}

        <form id="guessForm" onSubmit={handleSubmit}>
            <input
                disabled={tries >= 5}
                className={`guess-input ${shake ? "shake" : ""}`}
                onAnimationEnd={() => setShake(false)}
                type="text"
                value={guess}
                maxLength={15}
                onChange={(e) => setGuess(e.target.value)}
            />

          <br />
          <br />

          <button type="submit">Guess</button>
        </form>

          {message && (
              <div style={{ marginTop: "10px", fontSize: "18px" }}>
                  {message}
              </div>
          )}
          <p>{tries}/5 attempts used</p>
        <div style={{ marginTop: "30px" }}>
          {guesses.map((g, index) => (

              <div
                  key={index}
                  style={{
                    display: "flex",
                    justifyContent: "center",
                    gap: "5px",
                    marginBottom: "5px",
                  }}
              >
                {g.word.split("").map((letter, i) => (
                    <div
                        key={i}
                        style={{
                            width: "clamp(28px, 9vw, 40px)",
                            height: "clamp(28px, 9vw, 40px)",
                            border: "2px solid gray",
                            display: "flex",
                            alignItems: "center",
                            justifyContent: "center",
                            fontWeight: "bold",
                            fontSize: "clamp(14px, 5vw, 20px)",
                            backgroundColor: g.exactMatch ? "green" : g.validGuess ? "blue" : "red",
                            color: "white"
                        }}
                    >
                      {letter}
                    </div>
                ))}
              </div>
          ))}
        </div>
      </div>
  );
}

export default App
