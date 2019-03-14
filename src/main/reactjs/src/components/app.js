// @flow

import React from 'react';
import SearchBar from './search/search_bar';
import tvMazeClient from './TvMaze';
import type {searchItem} from './search/search-result';
import SearchResult from './search/search-result';
import Tile from "./tile";
import axios from "axios/index";

type props = {}

type state = {
    searchResult: Array<searchItem>,
    series: Array<string>,
    errorMessage: string,
    showSearchOverlay: boolean
}

export default class App extends React.Component<props, state> {

    constructor() {
        super();
        this.state = {
            searchResult: [],
            series: [],
            errorMessage: "",
            showSearchOverlay: false
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

    updateSearchResult(newResult: any) {
        let shows = [];

        newResult.forEach(function (item) {
            shows.push(item.show);
        });

        this.setState({searchResult: shows})
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
                <div className="container"
                     onClick={() => this.setState({showSearchOverlay: !this.state.showSearchOverlay})}>
                    {this.state.series.map((series) => <Tile series={series}/>)}
                </div>

                {/*search overlay*/}
                {this.state.showSearchOverlay &&
                <div id="overlay" onClick={() => this.setState({showSearchOverlay: !this.state.showSearchOverlay})}>
                    <h1>What do you want to stalk today?</h1>
                    < SearchBar
                        onSearch={(searchTerm) => tvMazeClient(searchTerm, (searchResults) => this.updateSearchResult(searchResults))}/>
                    <SearchResult searchResult={this.state.searchResult}/>
                </div>
                }
            </div>
        );
    }
}
