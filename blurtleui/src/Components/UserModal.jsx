import {useState} from 'react';
import api from "../api.js";

function UserModal({ onClose }) {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [message, setMessage] = useState("");


    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await api.post("/Blurtle/user/addUser", {
                firstName,
                lastName,
                email
            });

            setMessage(`User created: ${response.data.firstName} ${response.data.lastName}`)
            console.log("Created user:", response.data);

        }
        catch (error) {
            if(error.response){
                setMessage(error.response.data);
            }
            else{
                setMessage("Unable to contact server");
            }
            console.error("Failed to create user:", error);
        }
    };

    let feedback = null;
    if (message) {
        feedback = <p className="feedback">{message}</p>;
    }

    return (
        <div className="create-user-modal-overlay">
            <div className="create-user-modal">
                <h2>Create Player</h2>
                {feedback}
                <form onSubmit={handleSubmit}>
                    <input
                        type="text"
                        placeholder="First name"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                    />
                    <br/>
                    <input
                        type="text"
                        placeholder="Last name"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                    />
                    <br/>
                    <input
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                    <br/>
                    <button type="submit">Create User</button>
                    <br/>
                    <button type="button" onClick={onClose}>
                        Cancel
                    </button>
                </form>
            </div>
        </div>
    );
}

export default UserModal;