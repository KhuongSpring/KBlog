import './App.css'
import Login from "./components/auth/logins/login.jsx";
import Home from './components/home/home.jsx';
import Profile from "./components/profile/profile.jsx";
import Register from "./components/auth/registers/register.jsx";
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';

function App() {
    return (
        <div>
            <Login/>
        </div>,
            <Router>
                <Routes>
                    <Route path="/" element={<Login/>}/>
                    <Route path="/register" element={<Register/>}/>
                    <Route path="/home" element={<Home/>}/>
                    <Route path="/profile" element={<Profile/>}/>
                </Routes>
            </Router>
    )
}

export default App
