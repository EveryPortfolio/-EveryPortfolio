import React from 'react';
import styled from 'styled-components';

const Input = styled.input`
  display: block;
  color: white;
  padding-left: 5px;
  font-size: 25px;
  width: 70%;
  height: 45px;
  border-top: 0px;
  border-right: 0px;
  border-left: 0px;
  border-bottom: 1px solid white;
  background-color: transparent;
  ::placeholder,
  ::-webkit-input-placeholder {
    color: white;
    font-size: 20px;
  }
  :-ms-input-placeholder {
    color: white;
    font-size: 20px;
  }
  &::placeholder {
    font-size: 25px;
  }
`;

interface TextInputProps {
  placeholder: string;
  inputType: string;
  value: string;
  onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

export const TextInput = ({ placeholder, inputType, value, onChange }: TextInputProps): JSX.Element => {
  return (
    <Input placeholder={placeholder} autoComplete='off' type={inputType} value={value} onChange={onChange} />
  );
};
