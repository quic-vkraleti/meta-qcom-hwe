inherit autotools pkgconfig systemd

DESCRIPTION = "display Library"
LICENSE = "BSD-3-Clause & BSD-3-Clause-Clear"
BSD-3-Clause_LICENSE  = "file://sdm/include/core/display_interface.h;beginline=2;endline=22"
BSD-3-Clause-Clear_LICENSE = "file://sdm/include/core/display_interface.h;beginline=28;endline=29"

LIC_FILES_CHKSUM = " \
    ${BSD-3-Clause-Clear_LICENSE};md5=de893869f66f7d366d6b07f5cec50842 \
    ${BSD-3-Clause_LICENSE};md5=ef93dc3f1e145b6c1f89b90a5230ef8a \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI += "git://git.codelinaro.org/clo/le/platform/hardware/qcom/display.git;protocol=https;rev=a119db7d730dbaccf53db3e7de1616b3cb04c3b6;branch=display.qclinux.1.0.r1-rel"

S = "${WORKDIR}/git"

EXTRA_OECONF += " --with-sanitized-headers=${STAGING_INCDIR}/linux-kernel-qcom/usr/include"
EXTRA_OECONF += " --enable-displayle"

PACKAGECONFIG ?= " \
                 ${@bb.utils.contains('COMBINED_FEATURES', 'drm', 'drm', '', d)} \
                 "

PACKAGECONFIG[drm] = "--enable-sdmhaldrm, --disable-sdmhaldrm, libdrm, libdrm"

DEPENDS += "libdrm \
            gbm \
            linux-kernel-qcom-headers \
            displaydlkm \
            "

QDCM_JSON = "qdcm_calib_data_nt36672e_lcd_video_mode_dsi_novatek_panel_with_DSC.json"

do_install:append() {
  install -d ${D}/usr/data/display
  install -m 0644 ${S}/config/snapdragon_color_libs_config.xml \
-D ${D}/usr/data/display/snapdragon_color_libs_config.xml
  install -m 0644 ${S}/config/clstc_config_library.xml \
-D ${D}/usr/data/display/clstc_config_library.xml
  install -m 0644 ${S}/config/${QDCM_JSON} -D ${D}/usr/data/display/${QDCM_JSON}
}

PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"
FILES:${PN}  += " /usr/data/display/* "
FILES:${PN}  += " ${libdir}/* "
FILES:${PN}-dev  = " ${includedir}/* "
FILES:${PN}-dbg  = " ${libdir}/.debug/* "
