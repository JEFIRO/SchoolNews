import axios from "axios";

// Criando a instância do Axios
const api = axios.create({
  baseURL: 'http://localhost:8080',
});

api.interceptors.request.use((config) => {
  const token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJKZWZpcm8uZGV2Iiwic3ViIjoiamVmaXJvb0BnbWFpbC5jb20iLCJyb2xlcyI6WyJST0xFX0FETUlOIiwiUk9MRV9FRElUT1IiLCJST0xFX1NUVURFTlQiXSwiZXhwIjoyMDU3MTA5NzE3fQ.wbws9MndnQK3UAZ2ETsINcl7rpozFdZVA5-lusK1B_Y"
  
  
  if (token) {
    config.headers['Authorization'] = `Bearer ${token}`; 
  }

  return config;
}, (error) => {
  return Promise.reject(error);
});

export default api;
