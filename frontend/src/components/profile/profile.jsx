import React, {useEffect} from 'react';
import {useNavigate} from 'react-router-dom';
import styles from "./profile.module.css"

function Profile() {
    const navigate = useNavigate();

    const moveToEditProfile = async() => {
        navigate('/edit_profile')
    }

    useEffect(() => {
    }, []);  // Chạy chỉ một lần khi component load

    return (
        <div className={styles.profile_body}>
            <div className={styles.header}>
                <div className={styles.header_content}>
                    <div className={styles.avata}><img src="/profile/logo.png" alt=""/></div>
                    <div className={styles.information}>
                        <div className={styles.name_profile_edit}>
                            <p> kguonh </p>
                            <button className={styles.edit} onClick={moveToEditProfile}>Edit profile</button>
                            <img src="/profile/setting.png" alt=""/>
                        </div>
                        <div className={styles.follow}>
                            <p className={styles.post}>posts</p>
                            <p className={styles.followers}>followers</p>
                            <p className={styles.following}>following</p>
                        </div>
                        <p className={styles.profile_name}>Khương Phạm</p>
                        <p className={styles.description}>Dev begin</p>
                        <p className={styles.link_connect}><a href="#">Facbook</a></p>
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
