import {useNavigate} from 'react-router-dom';
import styles from './edit_profile.module.css'

function Edit_Profile(){
    return (
        <div className={styles.edit_profile_body}>
            <form action="">
                <div className={styles.content}>
                    <p className={styles.nav}>Edit profile</p>
                    <div className={styles.infor}>
                        <div className={styles.infor_left}>
                            <img src="/profile/edit_profile/avt.png" alt=""/>
                            <div>
                                <p className={styles.name}>kguonh</p>
                                <p className={styles.fullname}>Khuong Pham</p>
                            </div>
                        </div>
                        <div className={styles.infor_right}>
                            <button>Change photo</button>
                        </div>
                    </div>
                    <div className={styles.bio}>
                        <p>Bio</p>
                        <input type="text" placeholder="Bio"/>
                    </div>
                    <div className={styles.gender}>
                        <p className={styles.gender_header}>Gender</p>
                        <select className={styles.gender_select}>
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

