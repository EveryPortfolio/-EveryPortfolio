import React, { useState } from 'react';
import { useRouter } from 'next/router';
import { RightComponent } from '../../components/SignUpComponent/RightComponent/RightComponent';
import { validation } from '../../lib/validation';
import { createAPI, checkIDAPI } from '../../api/index';
import { messages } from '../../util/message';

export const RightContainer = (): JSX.Element => {
  const [email, setEmail] = useState('');
  const [duplication, setDuplication] = useState(false);
  const [password, setPassword] = useState('');
  const [repassword, setRePassword] = useState('');
  const [name, setName] = useState('');
  const router = useRouter();

  console.log('render Right');

  const onChangeEmail = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log('change Email');
    setEmail(e.target.value);
    if (duplication) setDuplication(false);
  };

  const onChangePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log('change password');
    setPassword(e.target.value);
  };

  const onChangeRePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log('change repassword');
    setRePassword(e.target.value);
  };

  const onChangeName = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log('change name');
    setName(e.target.value);
  };

  const onClick = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    e.preventDefault();
    console.log('click button');
    if (!duplication) {
      alert(messages.CHECKID);
      return;
    }
    const flag = validation({ id: email, password, repassword, name });
    if (flag) {
      alert(messages[flag]);
      return;
    }
    const params = { id: email, name, password };
    createAPI(params)
      .then((res) => {
        setEmail('');
        setPassword('');
        router.push('/');
      })
      .catch((err) => console.log('err check:', err));
  };

  const onCheck = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    e.preventDefault();
    checkIDAPI({ id: email })
      .then(() => {
        setDuplication(true);
        alert(messages.AVAILABLEID);
      })
      .catch(() => {
        setDuplication(false);
      });
  };

  return (
    <RightComponent
      email={email}
      onChangeEmail={onChangeEmail}
      password={password}
      onChangePassword={onChangePassword}
      onClick={onClick}
      name={name}
      onChangeName={onChangeName}
      repassword={repassword}
      onChangeRePassword={onChangeRePassword}
      onCheck={onCheck}
    />
  );
};
