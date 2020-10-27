module.exports = {
  preset: 'ts-jest',
  testEnvironment: 'enzyme',
  "setupFilesAfterEnv": [
    "jest-enzyme"
  ],
};
