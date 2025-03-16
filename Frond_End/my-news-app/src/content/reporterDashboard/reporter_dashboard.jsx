import React, { useState, useRef } from "react";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";
import axios from "axios";
import "./reporter-dashboard.css";
import api from "../../service/api";

const Dash = () => {
  const [author, setAuthor] = useState("");
  const [lead, setLead] = useState("");
  const [description, setDescription] = useState("");
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const quillRef = useRef(null);

  const handlePublish = async () => {
    const newsData = {
      member_id: "b42d2d85-a28e-4727-9a5d-2c8dbb1fd8c0",  
      title,
      description,
      imageMain: "someImage.jpg",  // Pode ser uma imagem do formulário ou um valor fixo
      imageMainDescription: "Main Image",
      author,
      lead,
      content,
    };

    try {
      const response = await api.post('/api/news/new', newsData,);

      console.log('Notícia publicada com sucesso:', response.data);
      
      setAuthor("");
      setLead("");
      setDescription("");
      setTitle("");
      setContent("");
    } catch (error) {
      console.error('Erro ao publicar notícia:', error);
    }
  };

  return (
    <div>
      <div className="Card">
        <h1>(Featured News Card)</h1>

        <p>
          Author:
          <input
            type="text"
            value={author}
            onChange={(e) => setAuthor(e.target.value)}
          />
        </p>

        <p>
          Lead:
          <textarea
            rows="3"
            cols="20"
            placeholder="Enter your text here..."
            value={lead}
            onChange={(e) => setLead(e.target.value)}
          ></textarea>
        </p>
        <p>
          Description:
          <textarea
            rows="3"
            cols="20"
            placeholder="Enter your text here..."
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          ></textarea>
        </p>
      </div>

      <div className="news_content">
        <h1>News Content</h1>
        <p>
          Title:
          <textarea
            rows="3"
            cols="20"
            placeholder="Enter your text here..."
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          ></textarea>
        </p>
        <ReactQuill
          ref={quillRef}
          value={content}
          onChange={setContent}
          id="editor"
        />
        <button onClick={handlePublish}>Publish</button>
      </div>
    </div>
  );
};

export default Dash;
