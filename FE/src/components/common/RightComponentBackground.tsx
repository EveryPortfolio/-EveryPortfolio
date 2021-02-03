import React from 'react';
import styled from 'styled-components';

const RightComponent = styled.div`
  width: 40%;
  padding: 10% 5%;
  padding-bottom: 0px;
  position: relative;

  &::after {
    width: 100%;
    height: 100%;
    z-index: -1;
    background-color: black;
    opacity: 0.7;
    content: '';
    position: absolute;
    left: 0;
    top: 0;
  }

  input {
    margin-top: 80px;
  }

  button {
    margin-top: 7%;
    margin-left: 20%;
  }
`;

export const RightComponentBackGround = ({ children }: { children: JSX.Element[] }): JSX.Element => {
  return <RightComponent>{children}</RightComponent>;
};
