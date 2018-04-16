import React from 'react';
import SearchBar from './search_bar';
import tvMazeClient from './TvMaze';
import SearchResult from './search-result';

export default class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            searchResult: []
        };
    }

    updateSearchResult(newResult) {
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
                    <SearchBar onSearch={(item) => tvMazeClient(item, (foo) => this.updateSearchResult(foo))}/>
                    <SearchResult searchResult={this.state.searchResult}/>
                </div>
            </div>
        );
    }
}
