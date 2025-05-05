import './App.module.css';
import Login from "./components/auth/logins/login.jsx";
import Home from './pages/home/home.jsx';
import Profile from "./pages/profile/profile.jsx";
import Register from "./components/auth/registers/register.jsx";
import Edit_Profile from "./pages/profile/edit_profile/edit_profile.jsx";
import Sidebar from "./components/Sidebar";
import {BrowserRouter, Routes, Route} from "react-router-dom";
import {useState, useEffect} from "react";
import styles from './App.module.css'

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem("token") !== null);

    const handleLogout = () => {
        localStorage.removeItem("token");
        setIsLoggedIn(false);
    }

    useEffect(() => {
        if (localStorage.getItem("token")) {
            setIsLoggedIn(true);
        }
    }, []);

    return (
        <BrowserRouter>
            {isLoggedIn ? (
                <div className={styles.flex}>
                    <Sidebar onLogout = {handleLogout} />
                    <div className={styles.main_content}>
                        <Routes>
                            <Route path="/" element={<Home/>}/>
                            <Route path="/profile" element={<Profile/>}/>
                            <Route path="/edit_profile" element={<Edit_Profile/>}/>
                        </Routes>
                    </div>
                </div>
            ) : (
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/login" element={<Login onLogin={() => setIsLoggedIn(true)}/>}></Route>
                    <Route path="/register" element={<Register/>}></Route>
                </Routes>
            )}

        </BrowserRouter>
    );
}

export default App;
