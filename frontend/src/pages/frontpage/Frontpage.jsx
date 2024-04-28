import React, {useState} from "react";
import DatePicker from "react-datepicker";
import logo from "../../img/hotel1.jpg";
import logo2 from "../../img/hotel2.jpg";
import logo3 from "../../img/hotel3.jpg";
import SearchForm from "../../components/SearchForm";

function Frontpage() {



    const cardsData = [
        { img: logo, text: "OMG Stay Finder is the best!", rating: "★★★★★" },
        { img: logo2, text: "These guys have the best deals!", rating: "★★★★★" },
        { img: logo3, text: "Stay Finder for the win!", rating: "★★★★★" }
    ];

    // Track the current index of the shown card
    const [currentIndex, setCurrentIndex] = useState(0);


    // Calculate and update the visible cards based on the current index
    const visibleCards = () => {
        const totalCards = cardsData.length;
        const indexes = [
            currentIndex % totalCards,
            (currentIndex + 1) % totalCards,
            (currentIndex + 2) % totalCards,
        ];

        return indexes.map(index => cardsData[index]);
    };



    return (
        <div>
            <div className="static navbar h-48 bg-header flex flex-col">
                <div className="flex flex-col text-white text-2xl font-bold">
                    <p>We at Stay Finder want to help you</p>
                    <p>Booking...</p>
                    <p>Even More...</p>

                </div>

                <div className="static navbar h-14 bg-header flex flex-col">
                    <SearchForm/>
                </div>


            </div>


            <h2 className="subheading mt-4">Get the best experience with Stay Finder</h2>
            <div className="flex justify-around relative">
                {visibleCards().map((card, index) => (
                    <div key={index} className="card">
                        <img src={card.img} alt={`Hotel ${index + 1}`}/>
                        <p className='justify-center'>{card.text}</p>
                        <div className="justify-center custom-color">{card.rating}</div>
                    </div>
                ))}

                <button
                    id="Shuffle_reviews"
                    className="shuffle col-span-1"
                    onClick={() => setCurrentIndex(currentIndex + 1)}
                >
                    <span className="arrow"/>
                </button>
                <button
                    id="Shuffle_reviews2"
                    className="shuffle2 col-span-1"
                    onClick={() => setCurrentIndex(currentIndex - 1)}
                    style={{display: currentIndex === 0 ? 'none' : 'block'}}
                >
                    <span className="arrow2"/>
                </button>
            </div>
        </div>

    );
}

export default Frontpage;