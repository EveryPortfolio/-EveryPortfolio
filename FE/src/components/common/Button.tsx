import React from 'react';
import styled from 'styled-components';

const ButtonStyle = styled.button`
  background-color: transparent;
  border: 1px solid white;
  color: white;
  text-align: center;
`;

interface ButtonProps {
  eventHandler: (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
  text: string;
}

export const Button = ({ eventHandler, text }: ButtonProps): JSX.Element => {
  return <ButtonStyle onClick={(e) => eventHandler(e)}>{text}</ButtonStyle>;
};
