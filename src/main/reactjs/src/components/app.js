//@flow

import React from 'react';
import SearchBar from './search_bar';
import tvMazeClient from './TvMaze';
import SearchResult from './search-result';

export default class App extends React.Component<{}, { [string]: any }> {

    constructor() {
        super();
        this.state = {
            searchResult: []
        };
    }

    updateSearchResult(newResult: Array<{ show: string }>) {
        let shows = [];

        newResult.forEach(function (item) {
            shows.push(item.show);
        });

        this.setState({searchResult: shows})
    }

    render() {
        return (
            <div>
                <h1>
                    What do you want to stalk today?
                </h1>
                <div>
                    <SearchBar
                        onSearch={(searchTerm) => tvMazeClient(searchTerm, (searchResults) => this.updateSearchResult(searchResults))}/>
                    <SearchResult searchResult={this.state.searchResult}/>
                </div>
            </div>
        );
    }
}
