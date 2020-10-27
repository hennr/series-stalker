import { render } from "enzyme";
import React from "react";
import type {image, searchItem} from "./search-result"
import SearchResult from "./search-result";

describe("search result", () => {
    it('renders with an empty search result list', () => {
        const div = document.createElement('div');
        render(<SearchResult searchResult={[]}/>, div);
    });

    it('renders with a complete search result list', () => {
        const div = document.createElement('div');
        const image: image = {
            medium: ""
        };
        const result: searchItem  = {
            id: "666",
            name: "evil dead",
            image: image
        };
        render(<SearchResult searchResult={[result]}/>, div);
    });

    it('renders with a missing medium size image', () => {
        const div = document.createElement('div');
        const result: searchItem  = {
            id: "666",
            name: "evil dead"
        };
        render(<SearchResult searchResult={[result]}/>, div);
    });
});
