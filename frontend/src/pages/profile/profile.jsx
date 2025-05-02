import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import styles from "./profile.module.css"
import {jwtDecode} from 'jwt-decode';


function Profile() {
    const navigate = useNavigate();
    const [userName, setUserName] = useState('');
    const [bio, setBio] = useState('');
    const [connectLink, setConnectLink] = useState('');
    const [fullName, setFullName] = useState('');
    const [gender, setGender] = useState('');

    const moveToEditProfile = async () => {
        navigate('/edit_profile')
    }

    const getUserNameByToken = async () => {
        const token = localStorage.getItem("token");
        if (!token) {
            console.error("Token not found!");
            return;
        }

        const decode = jwtDecode(token);
        const username = decode.sub;
        return username;
    }

    const fetchUserByUserName = async () => {
        const token = localStorage.getItem("token");
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

    useEffect(() => {
        fetchUserByUserName();
    }, []);  // Chạy chỉ một lần khi component load

    return (
        <div className={styles.profile_body}>
            <div className={styles.header}>
                <div className={styles.header_content}>
                    <div className={styles.avata}><img src="/profile/logo.png" alt=""/></div>
                    <div className={styles.information}>
                        <div className={styles.name_profile_edit}>
                            <p> {userName} </p>
                            <button className={styles.edit} onClick={moveToEditProfile}>Edit profile</button>
                            <img src="/profile/setting.png" alt=""/>
                        </div>
                        <div className={styles.follow}>
                            <p className={styles.post}>posts</p>
                            <p className={styles.followers}>followers</p>
                            <p className={styles.following}>following</p>
                        </div>
                        <p className={styles.profile_name}>{fullName}</p>
                        <p className={styles.description}>{bio}</p>
                        <p className={styles.link_connect}><a href={connectLink} target="_blank" rel="noopener noreferrer">{connectLink}</a></p>
                    </div>
                </div>
            </div>
            <div className={styles.story_mark}></div>
            <div className={styles.type_post}></div>
            <div className={styles.post}></div>
        </div>
    );
}

export default Profile;
