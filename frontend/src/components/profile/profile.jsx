import React, {useEffect} from 'react';
import "./profile.css"

function Profile() {


    useEffect(() => {
    }, []);  // Chạy chỉ một lần khi component load

    return (
        <div>
            <div className="header">
                <div className="avata"><img src="/vite.svg" alt=""/></div>
                <div className="information">
                    <div className="name-profile_edit">
                        <p> kguonh </p>
                        <button className="edit">Edit profile</button>
                        <img src="./setting.png" alt=""/>
                    </div>
                    <div className="follow">
                        <p className="post">posts</p>
                        <p className="followers">followers</p>
                        <p className="following">following</p>
                    </div>
                    <p className="profile-name">Khương Phạm</p>
                    <p className="description">Dev begin</p>
                    <p className="link-connect"><a href="#">Facbook</a></p>
                </div>
            </div>
            <div className="story-mark"></div>
            <div className="type-post"></div>
            <div className="post"></div>
        </div>
    );
}

export default Profile;
