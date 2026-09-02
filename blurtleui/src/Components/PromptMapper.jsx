
export default function PromptMapper({word, guess}){

    const guessArr = new Array(26).fill(0);
    guess.split("").forEach((letter) =>{
        guessArr[letter.charCodeAt(0) - 'A'.charCodeAt(0)]++;
    })

    return(
        <div className="prompt">
            {word.split("").map((letter, index) => {

                let highlight = false;
                const letterIndex = letter.charCodeAt(0) - 'A'.charCodeAt(0);

                if (guessArr[letterIndex] > 0){
                    highlight = true;
                    guessArr[letterIndex]--;
                }

                return(
                  <div
                      key={index}
                      className={`prompt-letter ${(!highlight ? "" : "highlight")}`}>
                      {letter}
                  </div>
                );
            })}
        </div>
    )
}

