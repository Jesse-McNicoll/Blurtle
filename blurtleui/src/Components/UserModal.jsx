import {useState} from 'react';
import api from "../api.js";

function UserModal({ onClose }) {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await api.post("/Blurtle/user/addUser", {
                firstName,
                lastName,
                email
            });

            console.log("Created user:", response.data);
            onClose();
        }
        catch (error) {
            console.error("Failed to create user:", error);
        }
    };

    return (
        <div className="create-user-modal-overlay">
            <div className="create-user-modal">
                <h2>Create Player</h2>

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