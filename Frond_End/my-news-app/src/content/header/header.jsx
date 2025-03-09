import { useState } from "react";
import logo from "/src/assets/Logo.png";
import profileIcon from "/src/assets/profilePhoto.jpg";
import "./Header.css";

function Header() {
  const [menuOpen, setMenuOpen] = useState(false);

  return (
    <header>
      <div className="logo_icon">
        <a href="#">
          <img src={logo} alt="logo-image" />
        </a>
      </div>
      
      <div className="profile_icon">
        <img 
          src={profileIcon} 
          alt="profilePhoto" 
          onClick={() => setMenuOpen(!menuOpen)} 
        />
      </div>

      {menuOpen && (
        <div className="menu">
          <ul>
            <li id="profile_config">
                <div id="profile_info">
                    <div>
                        <img src={profileIcon} alt="profilePhoto" />
                    </div>
                
                    <div>
                        <p>Jefferson Vitena</p>
                        <p>@Jefiroo</p>
                        
                    </div>
                </div>
                <div className="link-container">
                    <a href="http://youtube.com" >Profile</a>
                </div>
            </li>
            <li><a href="#">New report</a></li>
            <li><a href="#">Maneger user</a></li>
            <li><a href="#">Report Problem</a></li>
            <li><a href="#">LogOut</a></li>
          </ul>
        </div>
      )}
    </header>
  );
}

export default Header;
