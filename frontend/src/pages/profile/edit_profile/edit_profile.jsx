import {useNavigate} from 'react-router-dom';
import styles from './edit_profile.module.css';
import {useEffect, useState} from "react";
import {jwtDecode} from "jwt-decode";

function Edit_Profile() {
    const navigate = useNavigate();

    const [userName, setUserName] = useState('');
    const [fullName, setFullName] = useState('');
    const [bio, setBio] = useState('');
    const [connectLink, setConnectLink] = useState('');
    const [gender, setGender] = useState('');

    const token = localStorage.getItem("token");

    const getUserNameByToken = async () => {
        if (!token) {
            console.error("Token not found!");
            return;
        }

        const decode = jwtDecode(token);
        const username = decode.sub;
        return username;
    }

    const fetchUserByUserName = async () => {
        if (!token) {
            console.error("Token not found!");
            return;
        }

        const username = await getUserNameByToken();
        if (!username) {
            console.error("Username not found in token!");
            return null;
        }

        try {
            const response = await fetch(`http://localhost:8080/user/${username}`, {
                method: 'GET',
                headers: {
                    "Authorization": "Bearer " + token
                }
            });

            if (!response.ok) {
                throw new Error('Failed to fetch user info');
            }
            const userData = await response.json();

            setUserName(userData.username || '');
            setFullName(userData.fullName || '');
            setBio(userData.bio || '');
            setConnectLink(userData.connectLink || '');
            setGender(userData.gender || '');

            return userData;
        } catch (error) {
            console.error('Error fetching user info:', error);
            return null;
        }
    }

    const updateInformation = async () => {
        if (!token) {
            console.error("Token not found!");
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/user', {
                method: 'PUT',
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + token
                },
                body: JSON.stringify({userName, bio, connectLink, gender})
            });

            if (!response.ok) {
                throw new Error('Failed to fetch user info');
            }
            const userData = await response.json();

            setBio(userData.bio || '');
            setConnectLink(userData.connectLink || '');
            setGender(userData.gender || '');

            return userData;
        } catch (error) {
            console.error('Error fetching user info:', error);
            return null;
        }

    }

    const moveBack= async () =>{
        navigate('/profile');
    }

    useEffect(() => {
        fetchUserByUserName();
    }, []);

    return (
        <div className={styles.edit_profile_body}>
            <form onSubmit={updateInformation}>
                <div className={styles.content}>
                    <p className={styles.nav}>Edit profile</p>
                    <div className={styles.infor}>
                        <div className={styles.infor_left}>
                            <img src="/profile/edit_profile/avt.png" alt=""/>
                            <div>
                                <p className={styles.name}>{userName}</p>
                                <p className={styles.fullname}>{fullName}</p>
                            </div>
                        </div>
                        <div className={styles.infor_right}>
                            <button>Change photo</button>
                        </div>
                    </div>
                    <div className={styles.bio}>
                        <p>Bio</p>
                        <input type="text"
                               placeholder="Bio"
                               value={bio}
                               onChange={(e) => setBio(e.target.value)}
                        />
                    </div>
                    <div className={styles.connect_link}>
                        <p>Connect Link</p>
                        <input type="text"
                               placeholder="Link"
                               value={connectLink}
                               onChange={(e) => setConnectLink(e.target.value)}
                        />
                    </div>
                    <div className={styles.gender}>
                        <p className={styles.gender_header}>Gender</p>
                        <select className={styles.gender_select}
                                value={gender}
                                onChange={(e) => setGender(e.target.value)}>
                            <option value="MALE">MALE</option>
                            <option value="FEMALE">FEMALE</option>
                            <option value="OTHER">OTHER</option>
                        </select>
                        <p className={styles.des}>This won't be part of your public profile</p>
                    </div>
                    <div className={styles.submit}>
                        <button type="submit">Submit</button>
                    </div>
                </div>
            </form>
        </div>
    );
};

export default Edit_Profile;

