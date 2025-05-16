import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import styles from "./profile.module.css"
import {jwtDecode} from 'jwt-decode';


function Profile() {
    const navigate = useNavigate();
    const token = localStorage.getItem("token");

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
    const [myId, setMyId] = useState(0);
    const [targetId, setTargetId] = useState(0);
    const {idFromAnother} = useParams();

    const moveToEditProfile = async () => {
        navigate('/edit_profile')
    }

    const getIdByToken = async () => {
        const decoded = jwtDecode(token);
        return decoded.id;
    }

    const fetchUserById = async () => {
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

        let idFromToken = await getIdByToken();

        if (!idFromToken) {
            console.error("Id not found in token!");
            return null;
        }

        if (idFromAnother) {
            idFromToken = idFromAnother;
            setIsYourProfile(false);
        }

        try {
            const response = await fetch(`http://localhost:8080/user/id/${idFromToken}`, {
                method: 'GET',
                headers: {
                    "Authorization": `Bearer ${token}`,
                    "Content-Type": "application/json"
                }
            });

            if (!response.ok) {
                throw new Error('Failed to fetch user info');
            }
            const { result } = await response.json();

            setUserName(result.username || '');
            setFullName(result.fullName || '');
            setBio(result.bio || '');
            setConnectLink(result.connectLink || '');
            setGender(result.gender || '');
            setUrl(result.avatar || '');
            setFollower(result.follower || 0);
            setFollowing(result.following || 0);
            setPost(result.post || 0);

            setMyId(idFromToken);

            return result;
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
                    myId: myId,
                    targetId: targetId
                })
            });

            if (!response.ok) {
                throw new Error('Failed to follow');
            }
            const data = await response.json();
            return data;
        } catch (error) {
            console.error('Error fetching user info:', error);
            return null;
        }
    }

    useEffect(() => {
        if (idFromAnother) {
            setTargetId(idFromAnother);
            setIsYourProfile(false);
        } else {
            setIsYourProfile(true);
        }
        fetchUserById();
    }, [idFromAnother]);

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
                            <p className={styles.post}>
                                <strong style={{ fontSize: '1.1rem' }}>{post}</strong> posts
                            </p>
                            <p className={styles.followers}>
                                <strong style={{ fontSize: '1.1rem' }}>{follower}</strong> followers
                            </p>
                            <p className={styles.following}>
                                <strong style={{ fontSize: '1.1rem' }}>{following}</strong> following
                            </p>
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
