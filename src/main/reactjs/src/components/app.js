import React from 'react';
import SearchBar from './search_bar';
import tvMazeClient from './TvMaze';
import SearchResult from './search-result';
import Tile from "./tile";
import axios from "axios/index";

export default class App extends React.Component {

    constructor() {
        super();
        this.state = {
            searchResult: [],
            series: [],
            errorMessage: ""
        };
    }

    componentWillMount() {
        axios
            .get('/series/data', '{timeout: 3000}')
            .then(response => {
                this.setState({series: response.data});
            }).catch(e => {
                this.setState({errorMessage: "no series ids could be loaded"});
        });
    }

    updateSearchResult(newResult) {
        let shows = [];

        newResult.forEach(function (item) {
            shows.push(item.show);
        });

        this.setState({searchResult: shows})
    }

    static showSearchOverlay() {
        document.getElementById("overlay").style.visibility = "visible";
        document.getElementById("searchInput").focus();
    }

    static hideSearchOverlay() {
        document.getElementById("overlay").style.visibility = "hidden";
    }

    render() {

        if (this.state.errorMessage.length !== 0) {
            return (
                <h1 id="errorMessage">{this.state.errorMessage}</h1>
            );
        }

        return (
            <div>
                {/*tiles*/}
                <div className="container" onClick={App.showSearchOverlay}>
                    {this.state.series.map((series) => <Tile series={series}/>)}
                </div>

                {/*search overlay*/}
                <div id="overlay" onClick={App.hideSearchOverlay}>
                    <h1>What do you want to stalk today?</h1>
                    <SearchBar
                        onSearch={(searchTerm) => tvMazeClient(searchTerm, (searchResults) => this.updateSearchResult(searchResults))}/>
                    <SearchResult searchResult={this.state.searchResult}/>
                </div>
            </div>
        );
    }
}
