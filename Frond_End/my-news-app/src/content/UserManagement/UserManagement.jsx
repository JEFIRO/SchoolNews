import React, { useState, useEffect, use } from "react";
import api from "../../service/api";
import photo from "../../assets/profilePhoto.jpg"
import { Link } from "react-router-dom"; // Importe o Link

import "./userManagement.css";

const UserManagement = () => {
  const [users, setUsers] = useState([]);

  async function getUser() {
    try {
      const response = await api.get("/allMember");
      setUsers(response.data);
    } catch (error) {
      console.error("Erro ao buscar usuários", error);
    }
  }

  useEffect(() => {
    getUser();
  }, []);

  const formatBoolean = (value) => (value ? "active" : "inactive");

  return (
    <div className="user-management">
      <h1>Gerenciamento de Usuários</h1>

      <table>
        <thead>
          <tr>            
          <th>Id</th>
            <th>Name</th>
            <th>Role</th>  
            <th>DateCreated</th>   
            <th>Status</th>                   
            <th>publishedPermission</th>
            <th>accountNotLocked</th>
          </tr>
        </thead>
        <tbody>
          {users.length > 0 ? (
            users.map((user) => (
              <tr key={user._id}>
                <td><img src={photo} alt="profilePhoto" id="profilePhoto" /></td>
                <td><Link to={`/user/${user._id}`} key={user.id}> {user.name} </Link> </td>
                <td>{user.role}</td>            
                <td>{user.dateCreated}</td>
                <td>{formatBoolean(user.status)}</td>
                <td>{formatBoolean(user.publishedPermission)}</td>
                <td>{formatBoolean(user.accountNotLocked)}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4">Nenhum usuário encontrado</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default UserManagement;
