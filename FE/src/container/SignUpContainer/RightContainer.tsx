import React, { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import { RightComponent } from '../../components/SignUpComponent/RightComponent/RightComponent';
import { validation } from '../../lib/validation';
import { createAPI, checkIDAPI } from '../../api/index';
import { messages } from '../../util/message';
import { useDebounce, useInput } from '../../hooks';

export const RightContainer = (): JSX.Element => {
  const [email, setEmail] = useState('');
  const [duplication, setDuplication] = useState(false);
  const [password, setPassword, onChangePassword] = useInput('');
  const [repassword, setRePassword, onChangeRePassword] = useInput('');
  const [name, setName, onChangeName] = useInput('');
  const debounceEmail = useDebounce({ stateValue: email, delay: 2000 });
  const router = useRouter();

  console.log('render Right');

  useEffect(() => {
    if (debounceEmail === '') return;
    console.log('debounce effect');
    checkIDAPI({ id: debounceEmail })
      .then(() => {
        setDuplication(true);
        alert(messages.AVAILABLEID);
      })
      .catch(() => {
        setDuplication(false);
      });
  }, [debounceEmail]);

  const onChangeEmail = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log('change Email');
    setEmail(e.target.value);
    if (duplication) setDuplication(false);
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
        setRePassword('');
        setName('');
        router.push('/');
      })
      .catch((err) => console.log('err check:', err));
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
      duplication={duplication}
    />
  );
};
