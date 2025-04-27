import {useNavigate} from 'react-router-dom';
import {useState} from "react";
import styles from './register.module.css'

function Register() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [fullName, setFullName] = useState('');

    const navigate = useNavigate();

    const handleSignUp = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/auth/register', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({username, password, fullName, email}),
            });

            if (!response.ok) {
                const err = await response.text();
                throw new Error(err);
            }

            const data = await response.json();
            console.log('Register success:', data);

            localStorage.setItem("token", data.token);

            navigate("/")

        } catch (error) {
            console.error('SignUp error:', error.message);
        }
    };

    return (
        <div className={styles.register_body}>
            <form onSubmit={handleSignUp}>
                <div className={styles.content}>
                    <img src="/register/logo.png" alt="" className={styles.logo}/>
                    <div className={styles.preview}>
                        <p>Sign up to see photos and videos from your friend</p>
                    </div>
                    <div className={styles.outh2fb}>
                        <button type="submit" value="" className={styles.login_fb}>
                            <img src="/register/fb.png" alt=""/>
                            <div>
                                <p>Log in with Facebook</p>
                            </div>
                        </button>
                    </div>
                    <p className={styles.or}>---------------------- OR ----------------------</p>
                    <div>
                        <input type="text"
                               placeholder="Username"
                               className={styles.username}
                               value={username}
                               onChange={(e)=> setUsername(e.target.value)}
                        />
                    </div>
                    <div>
                        <input type="password"
                               placeholder="Password"
                               className={styles.password}
                               value={password}
                               onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                    <div>
                        <input type="text"
                               placeholder="Fullname"
                               className={styles.fullname}
                               value={fullName}
                               onChange={(e) => setFullName(e.target.value)}
                        />
                    </div>
                    <div>
                        <input type="text"
                               placeholder="Email"
                               className={styles.email}
                               value={email}
                               onChange={(e) => setEmail(e.target.value)}
                        />
                    </div>
                    <div className={styles.policy}>By signing up, you agree to our Terms , Privacy Policy and Cookies Policy
                    </div>
                    <div>
                        <button type="submit" className={styles.btn_register }>Sign up</button>
                    </div>
                </div>
                <div className={styles.login}>
                    <p>Have an account? </p>
                    <a href="/">Login</a>
                </div>
            </form>
        </div>
    );
}

export default Register;
