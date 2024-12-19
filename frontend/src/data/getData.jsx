export const loginUser = async (email, password) => {
    try {
        const response = await fetch("http://127.0.0.1:8080/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ email, password }),
        });

        if (response.ok) {
            const data = await response.json();
            return data;
        } else {
            throw new Error("Login failed");
        }
    } catch (error) {
        console.error("Error during login:", error);
        throw error;
    }
};

