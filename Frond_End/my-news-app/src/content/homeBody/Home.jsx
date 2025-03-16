import { useEffect, useState } from "react";
import "./Home.css";
import news from "/src/assets/news.avif";
import api from "../../service/api";

function Home() {
  const [home, setHomeNews] = useState([]);

  async function getNews() {
    try {
      const homeApi = await api.get("/api/news/home");
      setHomeNews(homeApi.data);
    } catch (error) {
      console.error("Erro ao buscar notícias", error);
    }
  }

  useEffect(() => {
    getNews();
  }, []);

  return (
    <div>
      {home.map((item) => (
        <div key={item.id} className="content">
          <div>
            <img src={item.imageMain} alt={item.title} /> {/* Usando o título como descrição */}
          </div>

          <div className="news_content">
            <div>
              <h3>{item.title}</h3>
              <p>{item.lead}</p>
            </div>
            <div className="editor_info">
              <p>Há {item.datePublished}</p>
              <p>Por {item.author}</p>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}

export default Home;
