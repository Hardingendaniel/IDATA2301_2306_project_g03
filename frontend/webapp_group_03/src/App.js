import './App.css';
import  './components/Footer.css';
import './components/Header.css'
import './pages/frontpage/FrontPage.css'
import './pages/browsepage/browseStyle.css'
import './pages/hotelpage/hotelStyle.css'
import { MainContent } from "./MainContent";
import { BrowserRouter as Router } from "react-router-dom";
import Footer from "./components/Footer";
import Header from "./components/Header";

/**
 * A component representing the whole application
 * @return {JSX.Element}
 * @constructor
 */
export function App() {
  return (
      <Router>
        <Header />
        <MainContent />
        <Footer />
      </Router>
  );
}