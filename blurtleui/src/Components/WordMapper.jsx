import '../ComponentCSS/WordMapper.css'

function WordMapper({ guess }){
    return(
        <div className = "word-row">
        { guess.word.split("").map((letter, index) => (
            <div key={index} className={`letter-box ${guess.guessStatus === 'EXACT_MATCH' ? "correct" : guess.guessStatus === 'VALID_GUESS' ? "validGuess" : "wrong"}`}>
                {letter}
            </div>
            )
        )}
        </div>
    )
}

export default WordMapper;