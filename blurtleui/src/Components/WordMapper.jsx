import '../ComponentCSS/WordMapper.css'

function WordMapper({ guess }){
    return(
        <div className = "word-row">
        { guess.word.split("").map((letter, index) => (
            <div key={index} className={`letter-box ${guess.exactMatch ? "correct" : guess.validGuess ? "validGuess" : "wrong"}`}>
                {letter}
            </div>
            )
        )}
        </div>
    )
}

export default WordMapper;