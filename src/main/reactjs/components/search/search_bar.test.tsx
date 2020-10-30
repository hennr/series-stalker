import SearchBar from "./search_bar";
import { render, shallow } from 'enzyme';
import React from "react";

describe('search bar', () => {
    it('renders without crashing', () => {
        render(<SearchBar onSearch={() => {}}/>);
    });

    it('renders input field', () => {
        const wrapper = shallow(<SearchBar onSearch={() => {}} />);
        expect(wrapper.find("#searchInput")).toHaveLength(1);
    });
});
