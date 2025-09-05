package com.example.miniecommerce.service.UsuarioServices;

import com.example.miniecommerce.domain.Usuario;
import com.example.miniecommerce.infra.exception.UsuarioJaCadastrado;
import com.example.miniecommerce.infra.exception.UsuarioNaoEncontrado;
import com.example.miniecommerce.infra.repositories.UsuarioRepository;
import com.example.miniecommerce.web.dto.in.UsuarioCreateRequestDto;
import com.example.miniecommerce.web.dto.out.ListaUsuarioResponseDto;
import com.example.miniecommerce.web.dto.out.UsuarioDetailsResponseDto;
import com.example.miniecommerce.web.mapper.UsuarioMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CrudUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;


    public CrudUsuarioService(UsuarioMapper usuarioMapper,UsuarioRepository usuarioRepository,  PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }


    public UsuarioDetailsResponseDto cadastrar(UsuarioCreateRequestDto dto) {

        Usuario usuario = usuarioMapper.toUsuario(dto);

        usuarioRepository.findByEmail(usuario.getEmail())
                .ifPresent(u -> { throw new UsuarioJaCadastrado("Email em uso!"); });


        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);

        return usuarioMapper.toUsuarioDetailsReponseDto(usuario);

    }

    public Page<ListaUsuarioResponseDto> listar(Pageable pageable) {
        return usuarioRepository
                .findAll(pageable)
                .map(usuarioMapper::toListaResponseDto);
    }

    public UsuarioDetailsResponseDto deletar(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontrado("Id usuário inexistente"));

        usuario.setDeleted(true);

        usuarioRepository.save(usuario);


        return usuarioMapper.toUsuarioDetailsReponseDto(usuario);

    }

    public UsuarioDetailsResponseDto detalhar(Long id) {
        Usuario usuario = usuarioRepository
                .findById(id).orElseThrow(()-> new UsuarioNaoEncontrado("Usuario não encontrado!"));

        return usuarioMapper.toUsuarioDetailsReponseDto(usuario);
    }
}
