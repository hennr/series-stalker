// @flow

import React from 'react';
import SearchBar from './search/search_bar';
import tvMazeClient from './tvmaze/TvMazeClient';
import type {searchItem} from './search/search-result';
import SearchResult from './search/search-result';
import type {series} from "./tile";
import Tile from "./tile";
import axios from "axios/index";

type props = {}

type state = {
    searchResult: Array<searchItem>,
    series: Array<series>,
    errorMessage: string,
    showSearchOverlay: boolean
}

export default class App extends React.Component<props, state> {

    constructor(props: props) {
        super(props);
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
            }).catch((e) => {
            this.setState({errorMessage: `no series ids could be loaded => ${e}`});
        });
    }

    updateSearchResult(newResult: any) {
        let shows = [];

        newResult.forEach(function (item) {
            shows.push(item.show);
        });

        this.setState({searchResult: shows})
    }

    hideOverlay(event: Event) {
        const target = event.target;

        if (target instanceof HTMLElement && target.getAttribute('id') === 'overlay') {
            this.setState({showSearchOverlay: false});
        } else {
            this.setState({showSearchOverlay: true});
        }
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
                     onClick={(event) => this.hideOverlay(event)}>
                    {this.state.series.map((series, index) => <Tile series={series} key={index}/>)}
                </div>

                {this.state.showSearchOverlay &&
                <div
                    id="overlay"
                    onClick={(event) => this.hideOverlay(event)}
                    style={{visibility: this.state.showSearchOverlay ? 'visible' : 'hidden'}}
                >
                    <h1>What do you want to stalk today?</h1>

                    <SearchBar
                        onSearch={(searchTerm) => tvMazeClient(searchTerm, (searchResults) => this.updateSearchResult(searchResults))}
                    />

                    <SearchResult searchResult={this.state.searchResult}/>
                </div>
                }
            </div>
        );
    }
}
