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


    useEffect(() => {
        fetch('http://localhost:8080/Blurtle/getScrambledWord')
            .then(response => response.json())
            .then(data => setPrompt(data))
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
            const res = await api.post("/postGuessWord", { guessWord : guess});
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


      <div style={{ textAlign: "center", marginTop: "50px" }}>
        <h1>Blurtle</h1>
        <h4>Unscramble the letters to form a word. <br/> Any word made from the available letters counts, but max points for using all of them!</h4>
        <div>
            <p style={{
                    textAlign: "left",
                    paddingLeft: "350px"}}>
                <span class="red-text"> Red </span>= Valid Word, but incorrect use of available letters<br/>
                <span class="blue-text"> Blue </span>= Valid Guess using only available letters, but not max points.<br/>
                <span class="green-text">Green </span>= Perfect Score!
            </p>
        </div>



          {prompt && <h4 style={{
              color: "#ffffff",
              fontWeight: "bold"
          }}>{prompt.scrambledWord}</h4>}

        <form id="guessForm" onSubmit={handleSubmit}>
          <input
              disabled={tries >= 5}
              className={shake ? "shake": ""}
              onAnimationEnd={() => setShake(false)}
              type="text"
              value={guess}
              maxLength={15}
              onChange={(e) => setGuess(e.target.value)}
              style={{
                textTransform: "uppercase",
                fontFamily: "monospace",
                fontSize: "24px",
                display: "inline-block",
                width: "300px",
                textAlign: "left",
                letterSpacing: "8px",
              }}
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
                          width: "40px",
                          height: "40px",
                          border: "2px solid gray",
                          display: "flex",
                          alignItems: "center",
                          justifyContent: "center",
                          fontWeight: "bold",
                          fontSize: "20px",
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
