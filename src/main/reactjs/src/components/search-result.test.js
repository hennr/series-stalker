import React from 'react';
import ReactDOM from 'react-dom';
import type {image, searchItem} from "./search-result"
import SearchResult from "./search-result";

describe("search result", () => {
    it('renders with an empty search result list', () => {
        const div = document.createElement('div');
        ReactDOM.render(<SearchResult searchResult={[]}/>, div);
    });

    it('renders with an complete search result list', () => {
        const div = document.createElement('div');
        const image: image = {
            medium: ""
        };
        const result: searchItem  = {
            id: "666",
            name: "evil dead",
            image: image
        };
        ReactDOM.render(<SearchResult searchResult={[result]}/>, div);
    });

    it('renders with a missing medium size image', () => {
        const div = document.createElement('div');
        const result: searchItem  = {
            id: "666",
            name: "evil dead",
            image: null
        };
        ReactDOM.render(<SearchResult searchResult={[result]}/>, div);
    });
});
