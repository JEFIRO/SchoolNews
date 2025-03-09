import "./Home.css";
import news from "/src/assets/news.avif";

function Home() {
  return (
    <div className="content">
      <div>
        <img src={news} alt="news_image" />   
      </div>

      <div className="news_content">
        <div>
            <p>'Estratégia de pressão máxima'</p>
          <h3 id="news_title">EUA encerram isenção do Iraque para compra de eletricidade iraniana</h3>

          <p id="news_subTitle">
            Objetivo da medida do governo Trump é não proporcionar "nenhum alívio econômico ou financeiro" ao Irã.
          </p>
        </div>

        <div className="editor_info">
          <p>Há 38 Minutos</p>
          <p>Por Jefiroo.Dev</p>
        </div>
      </div>
    </div>
  );
}

export default Home;
