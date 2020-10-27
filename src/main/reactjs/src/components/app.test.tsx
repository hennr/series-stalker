import { render } from "enzyme";
import React from "react";
import App from "./app";

describe("app", () => {
  it('renders without crashing', () => {
    render(<App/>);
  });
});
