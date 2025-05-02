import { NavLink } from "react-router-dom";
import styles from './sidebar.module.css';
import { FaHome, FaUser, FaSignOutAlt } from 'react-icons/fa';

export default function Sidebar({onLogout}) {
    return (
        <div className={styles.side}>
            <div className={styles.logo}>
                <img src="/sidebar/side.png"/>
            </div>
            <nav className={styles.navlink}>
                <NavLink to="/" className={styles.nav_item}>
                    <FaHome className={styles.nav_icon}/>
                    <span>Home</span>
                </NavLink>
                <NavLink to="/profile" className={styles.nav_item}>
                    <FaUser className={styles.nav_icon}/>
                    <span>Profile</span>
                </NavLink>
                <NavLink to="/login" onClick={onLogout} className={styles.nav_item}>
                    <FaSignOutAlt className={styles.nav_icon}/>
                    <span>Log out</span>
                </NavLink>
            </nav>
        </div>
    );
}