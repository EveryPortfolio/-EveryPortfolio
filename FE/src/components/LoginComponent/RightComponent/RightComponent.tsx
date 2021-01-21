import React from 'react';
import { RightComponentBackGround, TextDiv, TextInput, Button } from '../../common';
import { Links } from './Links/Links';

interface RightComponentProps {
  email: string;
  onChangeEmail: (event: React.ChangeEvent<HTMLInputElement>) => void;
  password: string;
  onChangePassword: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onClick: (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
}

export const RightComponent = ({
  email,
  onChangeEmail,
  password,
  onChangePassword,
  onClick,
}: RightComponentProps): JSX.Element => {
  return (
    <RightComponentBackGround>
      <TextDiv size='80px' margin='0px' text='LOGIN' />
      <TextInput placeholder='Email' inputType='text' value={email} onChange={onChangeEmail} />
      <TextInput placeholder='Password' inputType='password' value={password} onChange={onChangePassword} />
      <Button eventHandler={onClick} text='LOGIN' />
      <Links />
    </RightComponentBackGround>
  );
};
