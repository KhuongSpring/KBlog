import {NavLink} from "react-router-dom";
import styles from './sidebar.module.css';
import {FaHome, FaUser, FaSignOutAlt, FaSearch} from 'react-icons/fa';
import {useEffect, useState} from 'react';

export default function Sidebar({onLogout}) {
    const [isMobile, setIsMobile] = useState(window.innerWidth < 1250);
    const [isExpanded, setIsExpanded] = useState(false);

    useEffect(() => {
        const handleResize = () => setIsMobile(window.innerWidth < 1250);
        window.addEventListener('resize', handleResize);
        return () => window.removeEventListener('resize', handleResize);
    }, []);

    const toggleExpand = () => {
        setIsExpanded(!isExpanded);
    };

    return (
        (isExpanded ?
            <div className={`${styles.side} ${isExpanded ? styles.expanded : ''}`}>
                <div>
                    <div className={styles.logo}>
                        <img src="/sidebar/logoin.png"/>
                    </div>
                    <nav className={styles.navlink}>

                        <NavLink to="/" className={styles.nav_item}>
                            <FaHome className={styles.nav_icon}/>
                        </NavLink>

                        <NavLink className={styles.nav_item} onClick={toggleExpand}>
                            <FaSearch className={styles.nav_icon}/>
                        </NavLink>

                        <NavLink to="/profile" className={styles.nav_item}>
                            <FaUser className={styles.nav_icon}/>
                        </NavLink>

                        <NavLink to="/login" onClick={onLogout} className={styles.nav_item}>
                            <FaSignOutAlt className={styles.nav_icon}/>
                        </NavLink>
                    </nav>
                </div>
                <div className={styles.expand_area}>
                    <span className={styles.expand_area_text}>Search</span>
                    <div>
                        <input
                            type="text"
                            placeholder="Search"
                            className={styles.search_input}
                        />
                    </div>
                    <div className={styles.expand_brr}></div>
                    <p>Recent</p>
                    <div className={styles.search_result}></div>
                    <div className={styles.search_result}></div>
                </div>
            </div> :
            <div className={`${styles.side} ${isExpanded ? styles.expanded : ''}`}>
                <div className={styles.logo}>
                    <img src={isMobile || isExpanded ? "/sidebar/logoin.png" : "/sidebar/side.png"}/>
                </div>
                <nav className={styles.navlink}>

                    <NavLink to="/" className={styles.nav_item}>
                        <FaHome className={styles.nav_icon}/>
                        {!isExpanded && <span className={styles.nav_text}>Home</span>}
                    </NavLink>

                    <NavLink className={styles.nav_item} onClick={toggleExpand}>
                        <FaSearch className={styles.nav_icon}/>
                        {!isExpanded && <span className={styles.nav_text}>Search</span>}
                    </NavLink>

                    <NavLink to="/profile" className={styles.nav_item}>
                        <FaUser className={styles.nav_icon}/>
                        {!isExpanded && <span className={styles.nav_text}>Profile</span>}
                    </NavLink>

                    <NavLink to="/login" onClick={onLogout} className={styles.nav_item}>
                        <FaSignOutAlt className={styles.nav_icon}/>
                        {!isExpanded && <span className={styles.nav_text}>Log out</span>}
                    </NavLink>

                </nav>
            </div>
        )
    );
}