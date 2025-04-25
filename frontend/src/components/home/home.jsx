import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function Home() {

    const handleSignIn = async () => {
        const token = localStorage.getItem("token");
        if (!token) {
            console.error("Token not found!");
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/user', {
                method: 'GET',
                headers: {
                    "Authorization": "Bearer " + token
                }
            });

            if (!response.ok) {
                const err = await response.text();
                throw new Error(err);
            }

            const data = await response.json();
            console.log("✅ Authenticated user:", data);
        } catch (error) {
            console.error('Login error:', error.message);
        }
    };

    const navigate = useNavigate();

    const handelMoveToProfile = async () => {
        navigate('/profile');
    }

    useEffect(() => {
        handleSignIn();  // Gọi API khi component mount
    }, []);  // Chạy chỉ một lần khi component load

    return (
        <div>
            <h1>Welcome to KBlog Dashboard 🎉</h1>
            <button onClick={handelMoveToProfile}>Move to profile</button>
        </div>
    );
}

export default Home;
