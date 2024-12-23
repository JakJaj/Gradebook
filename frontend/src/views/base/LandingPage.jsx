import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../../data/getData";

function LandingPage() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async () => {
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
        }
    };

    return (
        <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
            <h1 className="text-4xl font-bold mb-8">Gradebook</h1>
            <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-sm">
                <h2 className="text-2xl font-semibold mb-6">Login</h2>
                <form>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="email">
                            E-mail
                        </label>
                        <input
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            id="email"
                            type="text"
                            placeholder="example@email.com"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />
                    </div>
                    <div className="mb-6">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="password">
                            Password
                        </label>
                        <input
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
                            id="password"
                            type="password"
                            placeholder="********"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                    <div className="flex items-center justify-between">
                        <button
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                            type="button"
                            onClick={handleLogin}
                        >
                            Sign In
                        </button>
                    </div>
                    <p className="text-xs text-gray-500 mt-4">Use the credentials provided by your tutor.</p>
                </form>
            </div>
        </div>
    );
}

export default LandingPage;