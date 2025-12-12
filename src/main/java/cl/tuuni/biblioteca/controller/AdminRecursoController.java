package cl.tuuni.biblioteca.controller;

import cl.tuuni.biblioteca.entity.Lenguaje;
import cl.tuuni.biblioteca.entity.Nivel;
import cl.tuuni.biblioteca.entity.RecursoEducativo;
import cl.tuuni.biblioteca.entity.TipoRecurso;
import cl.tuuni.biblioteca.service.RecursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin/recursos")
public class AdminRecursoController {

    private final RecursoService recursos;

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("title", "Admin - Recursos");
        model.addAttribute("items", recursos.listarTodosAdmin());
        return "admin/recursos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("title", "Nuevo recurso");
        model.addAttribute("item", RecursoEducativo.builder().activo(true).build());
        cargarEnums(model);
        return "admin/recursos/form";
    }

    @PostMapping
    public String crear(@ModelAttribute("item") RecursoEducativo item,
                        RedirectAttributes ra) {
        if (item.getUrl() == null) item.setUrl("");
        recursos.guardar(item);
        ra.addFlashAttribute("ok", "Recurso creado correctamente.");
        return "redirect:/admin/recursos";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("title", "Editar recurso");
        model.addAttribute("item", recursos.buscarPorId(id));
        cargarEnums(model);
        return "admin/recursos/form";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id,
                             @ModelAttribute("item") RecursoEducativo item,
                             RedirectAttributes ra) {
        var actual = recursos.buscarPorId(id);

        actual.setTitulo(item.getTitulo());
        actual.setDescripcion(item.getDescripcion());
        actual.setUrl(item.getUrl() == null ? "" : item.getUrl());
        actual.setTipo(item.getTipo());
        actual.setLenguaje(item.getLenguaje());
        actual.setNivel(item.getNivel());
        actual.setActivo(item.isActivo());

        recursos.guardar(actual);
        ra.addFlashAttribute("ok", "Recurso actualizado.");
        return "redirect:/admin/recursos";
    }

    @PostMapping("/{id}/toggle")
    public String toggle(@PathVariable Long id, RedirectAttributes ra) {
        var r = recursos.buscarPorId(id);
        r.setActivo(!r.isActivo());
        recursos.guardar(r);
        ra.addFlashAttribute("ok", r.isActivo() ? "Recurso activado." : "Recurso desactivado.");
        return "redirect:/admin/recursos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        recursos.eliminar(id);
        ra.addFlashAttribute("ok", "Recurso eliminado.");
        return "redirect:/admin/recursos";
    }

    private void cargarEnums(Model model) {
        model.addAttribute("tipos", TipoRecurso.values());
        model.addAttribute("lenguajes", Lenguaje.values());
        model.addAttribute("niveles", Nivel.values());
    }
}
