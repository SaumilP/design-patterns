import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import App from "./App";
import "./styles/index.css";
import "./styles/prose.css";

const redirect = window.sessionStorage.getItem("gh-pages-path");
if (redirect && window.location.pathname === "/design-patterns/") {
  window.history.replaceState(null, "", `/design-patterns${redirect}`);
  window.sessionStorage.removeItem("gh-pages-path");
}

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <BrowserRouter basename="/design-patterns">
      <App />
    </BrowserRouter>
  </React.StrictMode>,
);
