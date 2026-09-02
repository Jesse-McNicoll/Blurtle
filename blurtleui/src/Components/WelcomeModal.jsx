import "../ComponentCSS/WelcomeModal.css"

function WelcomeModal(props){
    return(
        <div className="modal-overlay">
            <div className="modal">
                <h2 className="modal-header"> Welcome to Blurtle! </h2>
                <div className="modal-div">
                    <h4 className="modal-header"> Unscramble the letters to form a word. <br/>
                        Any word made from the available letters counts, but max points for using all of them!</h4>
                    <div className="modal-rules">
                        <p>
                            <span className="red-text"> Red </span>= Valid Word, but uses unavailable letters<br/>
                            <span className="blue-text"> Blue </span>= Valid Guess using only the available letters, but not max points.<br/>
                            <span className="green-text">Green </span>= Perfect Score!
                        </p>
                    </div>
                </div>
                <button className="modal-button" onClick={props.updateModal}> Ok, I'm ready to play! </button>
            </div>
        </div>
    )
}

export default WelcomeModal;