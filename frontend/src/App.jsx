import './App.css'
import Login from "./components/auth/logins/login.jsx";
import Home from './components/home/home.jsx';
import Profile from "./components/profile/profile.jsx";
import Register from "./components/auth/registers/register.jsx";
import Edit_Profile from "./components/profile/edit_profile/edit_profile.jsx"
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';

function App() {
    return (
        <div>
            <Login/>
            <Home/>
            <Profile/>
            <Register/>
            <Edit_Profile/>
        </div>,
            <Router>
                <Routes>
                    <Route path="/" element={<Login/>}/>
                    <Route path="/register" element={<Register/>}/>
                    <Route path="/home" element={<Home/>}/>
                    <Route path="/profile" element={<Profile/>}/>
                    <Route path={"/edit_profile"} element={<Edit_Profile/>}/>
                </Routes>
            </Router>
    )
}

export default App
