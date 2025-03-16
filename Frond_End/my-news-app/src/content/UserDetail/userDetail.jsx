import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import api from "../../service/api";
import photo from "../../assets/profilePhoto.jpg"
import "./userDetail.css"
const UserDetail = () => {
  const { id } = useParams();
  const [user, setUser] = useState(null);

  useEffect(() => {
    async function getUserDetail() {
      try {
        const response = await api.get(`/find/${id}`);
        setUser(response.data);
      } catch (error) {
        console.error("Erro ao buscar dados do usuário", error);
      }
    }
    getUserDetail();
  }, [id]);

  return (
    <div>
      {user ? (
        <div className="Conteiner">
          <div className="profilePhoto">
              <img src={photo} alt="" />
          </div>
          <h1>{user.name}</h1>
          <p><strong>Role:</strong> {user.role} <button>Chenge</button></p>
          <p><strong>Status:</strong> {user.status ? "Ativo" : "Inativo"}</p>
          <p><strong>Data Criado:</strong> {user.dateCreated}</p>
          <p><strong>Permissão de Publicação:</strong> {user.publishedPermission ? "Sim" : "Não"}</p>
          <p><strong>Conta Não Bloqueada:</strong> {user.accountNotLocked ? "Sim" : "Não"}</p>
        </div>
      ) : (
        <p>Carregando...</p>
      )}
    </div>
  );
};

export default UserDetail; 