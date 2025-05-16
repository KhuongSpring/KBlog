import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import styles from "./profile.module.css"
import {jwtDecode} from 'jwt-decode';


function Profile() {
    const navigate = useNavigate();
    const [userName, setUserName] = useState('');
    const [bio, setBio] = useState('');
    const [connectLink, setConnectLink] = useState('');
    const [fullName, setFullName] = useState('');
    const [gender, setGender] = useState('');
    const [follower, setFollower] = useState(0);
    const [following, setFollowing] = useState(0);
    const [post, setPost] = useState(0);
    const [url, setUrl] = useState("");
    const [isYourProfile, setIsYourProfile] = useState(true);
    const [myUsername, setMyUsername] = useState('');
    const [targetUsername, setTargetUsername] = useState('');
    const {usernameFromAnother} = useParams();
    const token = localStorage.getItem("token");


    const moveToEditProfile = async () => {
        navigate('/edit_profile')
    }

    const getUserNameByToken = async () => {
        const decode = jwtDecode(token);
        return decode.sub;
    }

    const fetchUserByUserName = async () => {
        setUserName('');
        setFullName('');
        setBio('');
        setConnectLink('');
        setGender('');
        setUrl('');
        setFollower(0);
        setFollowing(0);
        setPost(0);
        setIsYourProfile(true);

        if (!token) {
            console.error("Token not found!");
            return;
        }

        let username = await getUserNameByToken();
        if (!username) {
            console.error("Username not found in token!");
            return null;
        }

        if (usernameFromAnother) {
            username = usernameFromAnother;
            setIsYourProfile(false);
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

            setUserName(userData.result.username || '');
            setFullName(userData.result.fullName || '');
            setBio(userData.result.bio || '');
            setConnectLink(userData.result.connectLink || '');
            setGender(userData.result.gender || '');
            setUrl(userData.result.avatar || '');
            setFollower(userData.result.follower || 0);
            setFollowing(userData.result.following || 0);
            setPost(userData.result.post || 0);
            return userData;
        } catch (error) {
            console.error('Error fetching user info:', error);
            return null;
        }
    }

    const handleFollow = async () => {
        try {
            const response = await fetch(`http://localhost:8080/user/follow`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": "Bearer " + token
                },
                body: JSON.stringify({
                    myUsername: myUsername,
                    targetUsername: targetUsername
                })
            });

            if (!response.ok) {
                throw new Error('Failed to fetch user info');
            }
            const data = await response.json();
            return data;
        } catch (error) {
            console.error('Error fetching user info:', error);
            return null;
        }
    }

    useEffect(() => {
        fetchUserByUserName();
    }, [usernameFromAnother]);

    return (
        <div className={styles.profile_body}>
            <div className={styles.header}>
                <div className={styles.header_content}>
                    <div className={styles.avata}><img src={url || "/profile/logo.png"} alt=""/></div>
                    <div className={styles.information}>
                        <div className={styles.name_profile_edit}>
                            <p> {userName} </p>
                            {isYourProfile
                                ? <button className={styles.edit} onClick={moveToEditProfile}>Edit profile</button>
                                : <button className={styles.follow_btn} onClick={handleFollow}>Follow</button>}
                            <img src="/profile/setting.png" alt=""/>
                        </div>
                        <div className={styles.follow}>
                            <p className={styles.post}>{post} posts</p>
                            <p className={styles.followers}>{follower} followers</p>
                            <p className={styles.following}>{following} following</p>
                        </div>
                        <p className={styles.profile_name}>{fullName}</p>
                        <p className={styles.description}>{bio}</p>
                        <p className={styles.link_connect}><a href={connectLink} target="_blank"
                                                              rel="noopener noreferrer">{connectLink}</a></p>
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
