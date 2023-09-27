import React from 'react'
import Container from 'react-bootstrap/Container';
import TeacherForm from '../../../../Components/Admin/TeacherForm'
import './styles.scss'

const AddTeacherPage = () => {

  return (
    <main>
      <Container>
        <section className='dashboard__container'>
          <TeacherForm />
        </section>
      </Container>
    </main>
  )
}

export default AddTeacherPage