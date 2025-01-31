import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../../data/student/postData";

function LandingPage() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const handleLogin = async (event) => {
        event.preventDefault();
        try {
            const data = await loginUser(email, password);
            const userRole = data.role;

            switch (userRole) {
                case "ADMIN":
                    navigate("/admin/dashboard");
                    break;
                case "STUDENT":
                    navigate("/student/dashboard");
                    break;
                case "PARENT":
                    navigate("/parent/dashboard");
                    break;
                case "TEACHER":
                    navigate("/teacher/dashboard");
                    break;
                default:
                    console.error("Unknown user role:", userRole);
            }
        } catch (error) {
            console.error("Login failed:", error);
            setError("Login failed. Please check your email and password.");
        }
    };

    return (
        <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
            <h1 className="text-4xl font-bold mb-8">Gradebook</h1>
            <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-sm">
                <h2 className="text-2xl font-semibold mb-6">Login</h2>
                <form onSubmit={handleLogin}>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="email">
                            E-mail
                        </label>
                        <input
                            type="email"
                            id="email"
                            value={email}
                            placeholder="example@mail.com"
                            onChange={(e) => setEmail(e.target.value)}
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            required
                        />
                    </div>
                    <div className="mb-6">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="password">
                            Password
                        </label>
                        <input
                            type="password"
                            id="password"
                            value={password}
                            placeholder="********"
                            onChange={(e) => setPassword(e.target.value)}
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            required
                        />
                    </div>
                    {error && (
                    <div className="mb-4 text-red-500 text-center">
                        {error}
                    </div>
                )}
                    <div className="flex items-center justify-between">
                        <button
                            type="submit"
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                        >
                            Login
                        </button>
                    </div>
                </form>
                <p className="text-xs text-gray-500 mt-4">Use the credentials provided by the teacher.</p>
            </div>
        </div>
    );
}

export default LandingPage;