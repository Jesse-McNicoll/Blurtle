//Modal for handling feedback results at either success or failure of Blurtle game
import "../ComponentCSS/FeedbackModal.css"
import WordMapper from "./WordMapper.jsx";
function FeedBackModal({ feedback = "Game over!", message= "Come back tomorrow!", onClick, guess }){
    return(
        <div className="feedback-modal-overlay">
            <div className="feedback-modal">
                <h2 className="feedback-modal-header">{feedback}</h2>
                <h4 className="feedback-modal-header">{message}</h4>
                <h5 className="feedback-modal-h5"> Final Guess: </h5>
                <div className="feedback-modal-word-mapper">
                    <WordMapper guess={guess}/>
                </div>
                <button className="feedback-modal-button" onClick={onClick}> View Game State </button>
            </div>
        </div>
    )
}

export default FeedBackModal;